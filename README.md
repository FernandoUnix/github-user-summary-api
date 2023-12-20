# Github user summary API

Spring Boot application that generates a GitHub user information summary for a given GitHub account and outputs it as JSON or XML.

Getting started
technologies we are going to use:

* Java 17
* Spring boot
* Maven
* Spring Cloud OpenFeign
* Postman
* Github API - https://api.github.com

## Get Summary

Request:

```
GET /github-summary/{username}
```

Response JSON default:

```json
{
    "username": "FernandoUnix",
    "githubLink": "https://github.com/FernandoUnix/alcateiadev-microservicos-udemy",
    "repositoryQuantity": 1,
    "languagePercentages": {
        "Java": 100
    },
    "repositories": [
        {
            "name": "alcateiadev-microservicos-udemy",
            "repositoryLink": "https://github.com/FernandoUnix/alcateiadev-microservicos-udemy",
            "description": "alcateiadev-microservicos-udemy",
            "language": "Java"
        }
    ]
}
```

Response XML:

We need to send 'Accept' and 'Content-Type' headers as 'application/xml'.

```xml
<GithubSummaryResponse>
    <username>FernandoUnix</username>
    <githubLink>https://github.com/FernandoUnix/alcateiadev-microservicos-udemy</githubLink>
    <repositoryQuantity>1</repositoryQuantity>
    <languagePercentages>
        <Java>100</Java>
    </languagePercentages>
<repositories>
    <repositories>
        <name>alcateiadev-microservicos-udemy</name>
        <repositoryLink>https://github.com/FernandoUnix/alcateiadev-microservicos-udemy</repositoryLink>
        <description>alcateiadev-microservicos-udemy<description/>
        <language>Java</language>
    </repositories>
</repositories>
</GithubSummaryResponse>
```

### How to run the project ?

- Download repo
- Go to root folder where we have pom.xml file and open terminal in this folder and execute ```mvn clean install``` and after clean install you can run ```mvn spring-boot:run```