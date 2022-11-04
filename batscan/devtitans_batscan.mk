# Herda as configurações do emulador (produto sdk_phone_x86_64)
$(call inherit-product, $(SRC_TARGET_DIR)/product/sdk_phone_x86_64.mk)

# Sobrescreve algumas variáveis com os dados do novo produto
PRODUCT_NAME := devtitans_batscan
PRODUCT_DEVICE := batscan
PRODUCT_BRAND := BatscanBrand
PRODUCT_MODEL := BatscanModel

# Copia o arquivo devtitans.txt para o /system/etc da imagem do Android
PRODUCT_COPY_FILES += \
    device/devtitans/HandsOn-DevTitans/batscan/devtitans.txt:system/etc/devtitans.txt \
    device/devtitans/HandsOn-DevTitans/batscan/batscan.rc:vendor/etc/init/batscan.rc \
    device/devtitans/HandsOn-DevTitans/batscan/bootanimation.zip:product/media/bootanimation.zip \
    device/devtitans/HandsOn-DevTitans/batscan/default_wallpaper.png:product/media/default_wallpaper.png
#
PRODUCT_SYSTEM_PROPERTIES += \
    ro.devtitans.name=Batscan \
    ro.config.wallpaper=product/media/default_wallpaper.png
#
PRODUCT_PRODUCT_PROPERTIES += \
    ro.product.devtitans.version=1.0
#
#PRODUCT_VENDOR_PROPERTIES += \
#    ro.vendor.devtitans.hardware=ModelB
#
## Seta o diretório de overlays
#PRODUCT_PACKAGE_OVERLAYS = device/devtitans/kraken/overlay
#
#PRODUCT_PACKAGES += \
#    UniversalMediaPlayer \
#    hello_c \
#    nano \
#    sl \
#    hello_cpp \
#    hello_daemon_cpp \
#    WebServiceVolley \
#    HelloJava \
#    smartlamp_client
#
#BOARD_SEPOLICY_DIRS += device/devtitans/kraken/sepolicy
#
## Smartlamp AIDL Interface
#PRODUCT_PACKAGES += devtitans.smartlamp
#
## Smartlamp Binder Service
#PRODUCT_PACKAGES += devtitans.smartlamp-service
#
## Device Framework Matrix (Declara que o nosso produto Kraken precisa do serviço smartlamp)
#DEVICE_FRAMEWORK_COMPATIBILITY_MATRIX_FILE := device/devtitans/kraken/device_framework_matrix.xml
#
## Cliente de Linha de Comando para o Serviço Smartlamp
#PRODUCT_PACKAGES += smartlamp_service_client
#
## App Privilegiado de Teste do Serviço Smartlamp
#PRODUCT_PACKAGES += SmartlampTestApp
#
## Manager
#PRODUCT_PACKAGES += devtitans.smartlampmanager