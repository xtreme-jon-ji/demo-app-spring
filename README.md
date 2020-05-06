# Spring Boot Deploy to PWS and K8s demo 

This project was generated using [Spring initializr](https://start.spring.io/)

This demo demonstrates how to quickly get a spring boot application up and running,
and how to deploy to Cloud Native platforms like [Pivotal Web Services (PWS)](https://run.pivotal.io) and K8s.

## Disclaimer
- This project just shows how to push an app quickly. It doesn't follow best practices such as Testing, CI/CD,
and proper build configurations. But, hopefully it can show how easy it is to push apps
to production with the right Cloud Native frameworks and tools!

### Running locally

```$bash
$ ./gradlew bootRun
```

To test:
```$bash
$ curl http://localhost:8080/api/v1/hello
```

### Deploying to PWS
#### Pre-requisites
 - [cf cli](https://docs.cloudfoundry.org/cf-cli/install-go-cli.html) installed
 - Org and Space (account) provisioned on [PWS](https://run.pivotal.io) or any [CFAR](https://www.cloudfoundry.org/application-runtime/)
  like [VMWare Tanzu Application Service](https://tanzu.vmware.com/application-service)
  
#### Steps

1. Build the application by running:
```$bash
$ ./gradlew assemble
```

2. Login using cf cli. This is an example for PWS:
```$bash
$ cf login -a https://api.run.pivotal.io
```

3. Ensure that [manifest.yml](/manifest.yml) is populated

4. Push!
```$bash
$ cf push
```

5. By default, the cf cli will use the `manifest.yml` in the current directory for deployment. 
The included default manifest requests a random route for the app. Watch your terminal output to
see the URL at which you can access the app.

6. Done! Query the appropriate URL.

### Deploying to K8s

#### Pre-requisites
 - K8s cluster provisioned. See [Tanzu Kubernetes Grid](https://tanzu.vmware.com/kubernetes-grid), [GKE](https://cloud.google.com/kubernetes-engine), 
    or [EKS](https://aws.amazon.com/eks/)
 - [kubectl](https://kubernetes.io/docs/tasks/tools/install-kubectl/) installed, and configured with context pointing to K8s cluster
 - Local [Docker](https://www.docker.com/products/docker-desktop) installed
 - [DockerHub](https://hub.docker.com/) account, to push and host container images. 
 This can be replaced with private container registries (such as [Harbor](https://goharbor.io/)), which is beyond the scope of this demo
  
1. Update the following files, and replace `yourUser/yourRepoNameHere` with the name of your own repository.
    Note that in subsequent steps, replace the same placeholder with the proper value.
    - [k8s-manifest.yml](/k8s-manifest.yml)
    - [build.gradle](/build.gradle)
2. Run a gradle sync to sync dependencies
3. Build the image using:
```$bash
$ ./gradlew bootBuildImage
``` 
4. Run the image locally:
```$bash
$ docker run -it --rm -p 8080:8080 yourUser/yourRepoNameHere:latest
```
5. Test that the application is running by making a request and checking the response:
```$bash
$ curl http://localhost:8080/api/v1/hello
```
6. Push to DockerHub:
```$bash
$ docker push yourUser/yourRepoNameHere:latest
```
7. Push to K8s:
```$bash
$ kubectl apply -f k8s-manifest.yml
```

8. Wait for the LoadBalancer public IP to come up, by watching the External IP for the `demo-app-spring` service.
Note: this was tested on GKE, but different K8s platforms may have different implementations/requirements for LoadBalancers.
```$bash
$ kubectl get services 
```

9. Done! Query the appropriate URL

#### Links
- [Creating Docker images with Spring Boot 2.3.0](https://spring.io/blog/2020/01/27/creating-docker-images-with-spring-boot-2-3-0-m1)
- [Spring Boot Docker Topical Guide](https://spring.io/guides/topicals/spring-boot-docker/)
- [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.3.0.RC1/gradle-plugin/reference/html/#build-image)

## Help?!
- Please reach out to me through github if you encounter any issues. Thanks for looking!