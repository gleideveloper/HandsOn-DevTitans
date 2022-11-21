#include "freertos/FreeRTOS.h"
#include "esp_wifi.h"
#include "esp_wifi_types.h"
#include "esp_system.h"
#include "esp_event.h"
#include "esp_event_loop.h"
#include "nvs_flash.h"

#define WIFI_CHANNEL_SWITCH_INTERVAL  (500)
#define WIFI_CHANNEL_MAX               (13)

#define N                              50
#define M                              5

uint8_t level = 0, channel = 1;

static wifi_country_t wifi_country = {.cc="CN", .schan = 1, .nchan = 13}; //Most recent esp32 library struct

typedef struct {
  unsigned frame_ctrl:16;
  unsigned duration_id:16;
  uint8_t addr1[6]; /* receiver address */
  uint8_t addr2[6]; /* sender address */
  uint8_t addr3[6]; /* filtering address */
  unsigned sequence_ctrl:16;
  uint8_t addr4[6]; /* optional */
} wifi_ieee80211_mac_hdr_t;

typedef struct {
  wifi_ieee80211_mac_hdr_t hdr;
  uint8_t payload[0]; /* network data ended with 4 bytes csum (CRC32) */
} wifi_ieee80211_packet_t;

static esp_err_t event_handler(void *ctx, system_event_t *event);
static void wifi_sniffer_init(void);
static void wifi_sniffer_set_channel(uint8_t channel);
static const char *wifi_sniffer_packet_type2str(wifi_promiscuous_pkt_type_t type);
static void wifi_sniffer_packet_handler(void *buff, wifi_promiscuous_pkt_type_t type);

struct Mac{
  char *addr;
};

static struct Mac only_mac[M];
static struct Mac rssi_mac[M];
static struct Mac buff_mac[N];
static char buffer_rssi_mac[22];
int count_mac = 0;

esp_err_t event_handler(void *ctx, system_event_t *event){
  return ESP_OK;
}

void wifi_sniffer_init(void){
  nvs_flash_init();
  tcpip_adapter_init();
  ESP_ERROR_CHECK( esp_event_loop_init(event_handler, NULL) );
  wifi_init_config_t cfg = WIFI_INIT_CONFIG_DEFAULT();
  ESP_ERROR_CHECK( esp_wifi_init(&cfg) );
  ESP_ERROR_CHECK( esp_wifi_set_country(&wifi_country) ); /* set country for channel range [1, 13] */
  ESP_ERROR_CHECK( esp_wifi_set_storage(WIFI_STORAGE_RAM) );
  ESP_ERROR_CHECK( esp_wifi_set_mode(WIFI_MODE_NULL) );
  ESP_ERROR_CHECK( esp_wifi_start() );
  esp_wifi_set_promiscuous(true);
  esp_wifi_set_promiscuous_rx_cb(&wifi_sniffer_packet_handler);
}

void wifi_sniffer_set_channel(uint8_t channel){
  esp_wifi_set_channel(channel, WIFI_SECOND_CHAN_NONE);
}

const char * wifi_sniffer_packet_type2str(wifi_promiscuous_pkt_type_t type){
  switch(type) {
  case WIFI_PKT_MGMT: return "MGMT";
  case WIFI_PKT_DATA: return "DATA";
  default:  
  case WIFI_PKT_MISC: return "MISC";
  }
}

void wifi_sniffer_packet_handler(void* buff, wifi_promiscuous_pkt_type_t type){
  if (type != WIFI_PKT_MGMT)
    return;

  const wifi_promiscuous_pkt_t *ppkt = (wifi_promiscuous_pkt_t *)buff;
  const wifi_ieee80211_packet_t *ipkt = (wifi_ieee80211_packet_t *)ppkt->payload;
  const wifi_ieee80211_mac_hdr_t *hdr = &ipkt->hdr;
  
  sprintf(buffer_rssi_mac, "%02d|%02x:%02x:%02x:%02x:%02x:%02x",
    /* RSSI */
    ppkt->rx_ctrl.rssi,
    /* ADDR2 */
    hdr->addr2[0],hdr->addr2[1],hdr->addr2[2],hdr->addr2[3],hdr->addr2[4],hdr->addr2[5]
  );
  while(count_mac < N){
      buff_mac[count_mac].addr = buffer_rssi_mac;
      //Serial.printf("BUFF_MAC[%i]: %s\n",count_mac, buff_mac[count_mac].addr);
      count_mac++;
  }
}

int contain_mac(char *mac){
  Serial.printf("*MAC: %s\n", mac);
  for(int j=0; j < M; j++){
    if(buff_mac[j].addr != mac){
      //Serial.printf("ONLY_MAC[%i]: %s\n",j, only_mac[j].addr);
      return 1;
    }
  }
  return 0;
}

void set_rssi_mac(){
    char *mac;
    for(int i = 0; i < M; ++i){
        for(int j = 0; j < N; j++){
            mac = &buff_mac[j].addr[4];
            if(contain_mac(mac) != 0){
                only_mac[i].addr = mac;
                rssi_mac[i].addr = buff_mac[j].addr;
                //Serial.printf("RSSI_MAC[%i]: %s\n",i, rssi_mac[i].addr);
                //Serial.printf("ONLY_MAC[%i]:     %s\n",i, only_mac[i].addr);
                break;
            }
        }
    }    
}
// the setup function runs once when you press reset or power the board
void setup() {
  // initialize digital pin 5 as an output.
  Serial.begin(9600);
  wifi_sniffer_init();
}

// the loop function runs over and over again forever
void loop() {
  String serialCommand;
    while (Serial.available() > 0) {
        char serialChar = Serial.read();
        serialCommand += serialChar;
        if (serialChar == '\n') {
            processCommand(serialCommand);
            serialCommand = "";
        }
    }
    delay(100);
}

void processCommand(String command) {
    command.trim();
    command.toUpperCase();
    if (command == "GET_SCAN"){
      set_rssi_mac();
      for(int i = 0; i < N; i++){
          //Serial.printf("RES GET_SCAN %s\n", only_mac[count_mac++].addr);
          //Serial.printf("BUFF_MAC[%i]: %s\n",i, buff_mac[i].addr);
          Serial.printf("RSSI_MAC[%i]: %s\n",i, rssi_mac[i].addr);
          Serial.printf("ONLY_MAC[%i]:     %s\n",i, only_mac[i].addr);
      }
      //memset(rssi_mac, 0, N);
    }else{
        Serial.println("ERR Unknown command.");
    }   
}
