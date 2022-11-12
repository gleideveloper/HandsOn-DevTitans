#pragma once                           // Inclui esse cabeçalho apenas uma vez

#include <fstream>                     // Classe ifstream
#include <android-base/properties.h>   // Função GetBoolProperty
#include <sys/stat.h>                  // Função e struct stat
#include <random>                      // Geração de números aleatórios (valores simulados)

using namespace std;                   // Permite usar string diretamente ao invés de std::string

namespace devtitans::batscan {       // Pacote Batscan

class Batscan {
    public:
        /**
         * Verifica se o diretório /sys/kernel/batscan existe. Se existir
         * o dispositivo SmartLamp está conectado via USB. Caso contrário,
         * verifica a propriedade devtitans.batscan.allow_simulated
         * para ver se valores simulados podem ser usados.
         *
         * Retorna:
         *      0: dispositivo não encontrado
         *      1: sucesso
         *      2: simulado (disp. não encontrado, mas usando valores simulados)
         */
        int connect();

        /**
         * Acessa e seta a intensidade do led.
         *
         * Valor retornado/setado:
         *      0: led desligado
         *      1 a 99: led ligado com intensidade ledValue
         *      100: led ligado com intensidade máxima
         */
        char * getScan(); //K:alterado de getLed()
        //bool setLed(int ledValue);

        
        /**
         * Acessa o nível de luminosidade atual conforme reportado
         * pelo sensor de luz -- LDR (light-dependent resistor).
         *
         * Retorna:
         *      0: completamente escuro
         *      1 a 99: nível de luminosidade
         *      100: completamente claro
         */
        //int getLuminosity();

        /**
         * Threshold - Limiar de luminosidade para ligar/desligar o led.
         * Ao executar esse método, o dispositivo SmartLamp entra no modo
         * automático, em que o led será ligado se a luminosidade for
         * menor que o limiar. Caso contrário, o led é desligado.
         *
         * Valor retornado/setado:
         *      0: completamente escuro
         *      1 a 99: nível de luminosidade
         *      100: completamente claro
         */
        //int getThreshold();
        //bool setThreshold(int thresholdValue);

    private:
        /**
         * Métodos para ler e escrever valores nos arquivos "scan", K> alterado de "led"
         * "ldr" ou "threshold" do diretório /sys/kernel/batscan.
         */
        char * readFileValue(string file);
        bool writeFileValue(string file, int value);

        /**
         * Armazena valores simulados para o caso do dispositivo não estar
         * conectado, mas a propriedade devtitans.batscan.allow_simulated
         * for true.
         */
         int simLedValue = 88;
         int simThresholdValue = 42;
         char simScanValue[21] = "-86|00:1B:C9:4B:E3:57"
};

} // namespace