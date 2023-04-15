pipeline{
  environment {
        registry = "ahmedchokri/wevioo"
        registryCredential = 'ahmedchokri'
        dockerImage = ''
    }
agent any
stages{
stage("service parametrage"){
when {
              expression { params.service == "Parametrage" }
          }
          {
          dir("Parametrage-Service"){
          echo 'here'
          }
          }
}
}
