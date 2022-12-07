#include "batscan_service.h"

namespace aidl::devtitans::batscan {
    ndk::ScopedAStatus BatscanService::connect(int32_t* _aidl_return) {
        *_aidl_return = this->batscan.connect();
        LOG(INFO) << "connect(): " << *_aidl_return;
        return ndk::ScopedAStatus::ok();
    }

    ndk::ScopedAStatus BatscanService::getScan(string* _aidl_return) {
        *_aidl_return = this->batscan.getScan();
        LOG(INFO) << "getScan(): " << *_aidl_return;
        return ndk::ScopedAStatus::ok();
    }

}