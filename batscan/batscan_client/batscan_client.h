#pragma once                      // Inclui esse cabeçalho apenas uma vez

#include <iostream>               // std::cout (char-out) e std::endl (end-line)
#include <string.h>               // Função strcmp
#include <stdlib.h>               // Função atoi

#include "batscan_lib.h"        // Classe Batscan

namespace devtitans::batscan {  // Pacote que a classe abaixo pertence

class BatscanClient {           // Classe

    public:
        void start(int argc, char **argv);

};

} // namespace