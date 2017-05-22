#!/bin/bash -ex

root=$(pwd -P)
mkdir -p $root/.m2/repository

# gather some data about the repo
source $root/ci/vars.sh

# Path to output JAR
src=$root/target/pz-jobcommon-LATEST.jar

# Build Spring-boot JAR
[ -f $src ] || mvn clean package -U -Dmaven.repo.local="$root/.m2/repository"

# stage the artifact for a mvn deploy
mv $src $root/$APP.$EXT

