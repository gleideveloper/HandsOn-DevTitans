#include "batscan_client.h"

using namespace std;                  // Permite usar o cout e endl diretamente ao invés de std::cout

namespace devtitans::batscan {      // Entra no pacote devtitans::hello

void BatscanClient::start(int argc, char **argv) {
    cout << "Cliente Batscan!" << endl;

    if (argc < 2) {
        cout << "Sintaxe: " << argv[0] << "  " << endl;
        cout << "    Comandos: get-scan, set-led, get-luminosity, get-threshold, set-threshold" << endl;
        exit(1);
    }

    Batscan batscan;             // Classe da biblioteca Batscan

// Comando get-scan
    if (!strcmp(argv[1], "get-scan")) {
        cout << "Valor do RSSI | Mac: " << batscan.getScan() << endl;
    }else {
        cout << "Comando inválido." << endl;
        exit(1);
    }
}

} // namespace

// MAIN

using namespace devtitans::batscan; // Permite usar HelloCpp diretamente ao invés de devtitans::hello::HelloCpp

int main(int argc, char **argv) {
    BatscanClient client;               // Variável hello, da classe HelloCpp, do pacote devtitans::hello
    client.start(argc, argv);             // Executa o método printHello
    return 0;
}
