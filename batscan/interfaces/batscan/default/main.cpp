#include "batscan_service.h"

using namespace aidl::devtitans::batscan;                // BatscanService (batscan_service.cpp)
using namespace std;                                       // std::shared_ptr
using namespace ndk;                                       // ndk::SharedRefBase

int main() {
    LOG(INFO) << "Iniciando Batscan AIDL Service ...";

    ABinderProcess_setThreadPoolMaxThreadCount(0);

    shared_ptr<BatscanService> batscan_service = SharedRefBase::make<BatscanService>();

    const string instance = std::string() + IBatscan::descriptor + "/default";   // devtitans.batscan.IBatscan/default
    binder_status_t status = AServiceManager_addService(batscan_service->asBinder().get(), instance.c_str());
    CHECK(status == STATUS_OK);

    LOG(INFO) << "Batscan AIDL Service iniciado com nome: " << instance;
    ABinderProcess_joinThreadPool();

    return EXIT_FAILURE;                                   // Não deve chegar nunca aqui
}
