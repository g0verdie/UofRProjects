################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
C_SRCS += \
../src/FEV.c \
../src/file2struct.c \
../src/put_get_put.c \
../src/struct2file.c 

OBJS += \
./src/FEV.o \
./src/file2struct.o \
./src/put_get_put.o \
./src/struct2file.o 

C_DEPS += \
./src/FEV.d \
./src/file2struct.d \
./src/put_get_put.d \
./src/struct2file.d 


# Each subdirectory must supply rules for building sources it contributes
src/%.o: ../src/%.c
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C Compiler'
	gcc -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


