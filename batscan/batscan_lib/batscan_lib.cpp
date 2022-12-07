#include "batscan_lib.h"

using namespace std;                                   // Permite usar string, ifstream diretamente ao invés de std::string
using namespace android::base;                         // Permite usar GetBoolProperty ao invés de android::base::GetBoolProperty

namespace devtitans::batscan {                       // Entra no pacote devtitans::batscan

int Batscan::connect() {
    char dirPath[] = "/sys/kernel/batscan";
    struct stat dirStat;
    if (stat(dirPath, &dirStat) == 0)
        if (S_ISDIR(dirStat.st_mode))
            return 1;                                  // Se o diretório existir, retorna 1

    // Diretório não existe, vamos verificar a propriedade
    bool allowSimulated = GetBoolProperty("devtitans.batscan.allow_simulated", true);
    if (!allowSimulated)
        return 0;                                      // Dispositivo não encontrado
    else
        return 2;                                      // Usando valores simulados
}

string Batscan::readFileValue(string file) {
    int connected = this->connect();

    if (connected == 2) {                               // Usando valores simulados
        if (file == "scan")
            return this->simScanValue;
    }

    else if (connected == 1) {                          // Conectado. Vamos solicitar o valor ao dispositivo
        string value;
        string filename = string("/sys/kernel/batscan/") + file;
        ifstream file(filename);                        // Abre o arquivo do módulo do kernel

        if (file.is_open()) {                           // Verifica se o arquivo foi aberto com sucesso
            file >> value;                              // Lê um inteiro do arquivo
            file.close();
            return value;
        }
    }

    // Se chegou aqui, não foi possível conectar ou se comunicar com o dispositivo
    return "-1";
}

string Batscan::getScan() {
    return this->readFileValue("scan");
}


} // namespace