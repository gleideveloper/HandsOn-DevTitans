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

    // ndk::ScopedAStatus SmartlampService::getLed(int32_t* _aidl_return) {
    //     *_aidl_return = this->smartlamp.getLed();
    //     LOG(INFO) << "getLed(): " << *_aidl_return;
    //     return ndk::ScopedAStatus::ok();
    // }

    // ndk::ScopedAStatus SmartlampService::setLed(int32_t in_ledValue, bool* _aidl_return) {
    //     *_aidl_return = this->smartlamp.setLed(in_ledValue);
    //     LOG(INFO) << "setLed( " << in_ledValue << "): " << (*_aidl_return ? "true" : "false");
    //     return ndk::ScopedAStatus::ok();
    // }

    // ndk::ScopedAStatus SmartlampService::getLuminosity(int32_t* _aidl_return) {
    //     *_aidl_return = this->smartlamp.getLuminosity();
    //     LOG(INFO) << "getLuminosity(): " << *_aidl_return;
    //     return ndk::ScopedAStatus::ok();
    // }
}
