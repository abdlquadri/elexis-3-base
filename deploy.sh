#!/usr/bin/env bash

P2=/var/www/vhosts/elexis.ch/httpdocs/ungrad/p2/elexis-base/${BUILD_NUMBER}

mkdir $P2

cp -R ch.elexis.base.p2site/target/repository/* $P2
