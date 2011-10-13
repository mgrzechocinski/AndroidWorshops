#!/bin/bash
keytool -genkey -v -keystore my.keystore -alias android_publish_key -keyalg RSA -keysize 2048 -validity 10000
