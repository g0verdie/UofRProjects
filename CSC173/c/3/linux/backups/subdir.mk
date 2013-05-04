################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
C_SRCS += \
../backups/parser.c \
../backups/scanner.c 

OBJS += \
./backups/parser.o \
./backups/scanner.o 

C_DEPS += \
./backups/parser.d \
./backups/scanner.d 


# Each subdirectory must supply rules for building sources it contributes
backups/%.o: ../backups/%.c
	@echo 'Building file: $<'
	@echo 'Invoking: Cygwin C Compiler'
	gcc -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


