################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../src/ActivityLogger.cpp \
../src/GroupActivityMonitor.cpp \
../src/LinuxActivityMonitor.cpp \
../src/LinuxActivityMonitorFactory.cpp \
../src/LinuxSystem.cpp \
../src/System.cpp \
../src/main.cpp 

OBJS += \
./src/ActivityLogger.o \
./src/GroupActivityMonitor.o \
./src/LinuxActivityMonitor.o \
./src/LinuxActivityMonitorFactory.o \
./src/LinuxSystem.o \
./src/System.o \
./src/main.o 

CPP_DEPS += \
./src/ActivityLogger.d \
./src/GroupActivityMonitor.d \
./src/LinuxActivityMonitor.d \
./src/LinuxActivityMonitorFactory.d \
./src/LinuxSystem.d \
./src/System.d \
./src/main.d 


# Each subdirectory must supply rules for building sources it contributes
src/%.o: ../src/%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -D__GXX_EXPERIMENTAL_CXX0X__ -I"/home/kagy/Gyuripr/AutoWorkLogger/AutoWorkLogger/include" -O3 -Wall -c -fmessage-length=0 -pthread -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


