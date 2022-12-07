#pragma once

#include <android-base/logging.h>
#include <android/binder_process.h>
#include <android/binder_manager.h>

#include <aidl/devtitans/batscan/BnBatscan.h>            // Criado pelo AIDL automaticamente

#include "batscan_lib.h"                                   // Classe Batscan (biblioteca)

using namespace devtitans::batscan;

namespace aidl::devtitans::batscan {

class BatscanService : public BnBatscan {
    public:
        ndk::ScopedAStatus connect(int32_t* _aidl_return) override;
        ndk::ScopedAStatus getScan(string* _aidl_return) override;

    private:
        Batscan batscan;                                 // Biblioteca
};

}