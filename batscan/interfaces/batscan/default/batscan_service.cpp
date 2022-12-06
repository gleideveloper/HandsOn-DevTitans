#include "batscan_service.h"

namespace aidl::devtitans::batscan {
    ndk::ScopedAStatus BatscanService::connect(int32_t* _aidl_return) {
        *_aidl_return = this->batscan.connect();
        LOG(INFO) << "connect(): " << *_aidl_return;
        return ndk::ScopedAStatus::ok();
    }

    ndk::ScopedAStatus BatscanService::getLed(int32_t* _aidl_return) {
        *_aidl_return = this->batscan.getLed();
        LOG(INFO) << "getLed(): " << *_aidl_return;
        return ndk::ScopedAStatus::ok();
    }

}