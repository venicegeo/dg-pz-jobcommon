@Library('pipelib@master') _

def THREADFIX_ID = env.THREADFIX_ID ? env.THREADFIX_ID : '115'

node {
  def mvn = tool 'M3'
  def root = pwd()

  stage('Setup') {
    git([
      url: env.GIT_URL ? env.GIT_URL : 'https://github.com/venicegeo/pz-jobcommon',
      branch: "master"
    ])
  }

  stage('Archive') {
    sh """
      ${mvn}/bin/mvn clean package -U -Dmaven.repo.local=${root}
      cp ${root}/target/pz-jobcommon-LATEST.jar ${root}/pz-jobcommon.jar
    """
    mavenPush()
  }

  stage('Scans') {
    dependencyCheck {
      threadfixId = THREADFIX_ID
    }
    ionConnect()
    sh """
      mkdir -p ${root}/.m2/repository
      ${mvn}/bin/mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent install \
        -Dmaven.repo.local=${root}/.m2/repository \
        -Pcoverage-per-test org.jacoco:jacoco-maven-plugin:report \
        -DdataFile=target/jacoco.exec
    """
    sonar()
    sh """
      ${mvn}/bin/mvn install:install-file -Dmaven.repo.local=${root} -Dfile=pom.xml -DpomFile=pom.xml
    """
    fortify {
      threadfixId = THREADFIX_ID
    }
  }

  stage('Cleanup') {
    deleteDir()
  }
}
