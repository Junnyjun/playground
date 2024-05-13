#!/bin/zsh

openssl req -newkey rsa:2048 -nodes -keyout junnyland.key -x509 -days 365 -out junnyland.crt
keytool -import -file junnyland.crt -alias junnyland -keystore junnyland.jks

132435!