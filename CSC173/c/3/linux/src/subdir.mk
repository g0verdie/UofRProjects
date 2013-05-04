################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
C_SRCS += \
../src/char_classes.c \
../src/html.c \
../src/interpreter.c \
../src/keywords.c \
../src/main.c \
../src/parser.c \
../src/reader.c \
../src/scanner.c \
../src/treebuild.c 

OBJS += \
./src/char_classes.o \
./src/html.o \
./src/interpreter.o \
./src/keywords.o \
./src/main.o \
./src/parser.o \
./src/reader.o \
./src/scanner.o \
./src/treebuild.o 

C_DEPS += \
./src/char_classes.d \
./src/html.d \
./src/interpreter.d \
./src/keywords.d \
./src/main.d \
./src/parser.d \
./src/reader.d \
./src/scanner.d \
./src/treebuild.d 


# Each subdirectory must supply rules for building sources it contributes
src/%.o: ../src/%.c
	@echo 'Building file: $<'
	@echo 'Invoking: Cygwin C Compiler'
	gcc -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


