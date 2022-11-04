# Inclui as configurações do "hardware" do emulador
include build/make/target/board/emulator_x86_64/BoardConfig.mk

#Permite inicializar o sistema de forma permissiva e verificar quais negações são encontradas.
BOARD_KERNEL_CMDLINE += androidboot.selinux=permissive