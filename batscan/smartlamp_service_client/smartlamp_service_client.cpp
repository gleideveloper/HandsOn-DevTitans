#include <android/binder_manager.h>
#include <aidl/devtitans/batscan/IBatscan.h>
#include <iostream>                             // std::cout e std::endl (end-line)

using namespace aidl::devtitans::batscan;       // ISmartlamp
using namespace std;                            // std::shared_ptr
using namespace ndk;                            // ndk::SpAIBinder

int main() {
    shared_ptr<IBatscan> service;
    service = IBatscan::fromBinder(SpAIBinder(AServiceManager_getService("devtitans.batscan.IBatscan/default")));
    
    if (!service) {
        cout << "Erro acessando o serviço!" << endl;
        return 1;
    }

    String _aidl_return;
    ScopedAStatus status = service->getScan(&_aidl_return);
    
	String rssi_mac = static_cast<String>(_aidl_return);
    
    cout << "Rssi Mac: " << rssi_mac << endl;
    
    return 0;
}
