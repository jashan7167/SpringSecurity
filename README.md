# Spring Security and OAuth2 Learning Guide

This repository serves as a learning guide for Spring Security and OAuth2. It provides examples and explanations to help you understand how to secure your applications using these technologies.

## Table of Contents

1. [Introduction](#introduction)
2. [Setting Up the Project](#setting-up-the-project)
3. [Basic Authentication](#basic-authentication)
4. [OAuth2 and JWT](#oauth2-and-jwt)
5. [Global Security](#global-security)
6. [Method Security](#method-security)
7. [Testing with Postman](#testing-with-postman)
8. [References](#references)

## Introduction

Spring Security is a powerful and highly customizable authentication and access-control framework for Java applications. OAuth2 is an open standard for access delegation commonly used for token-based authentication and authorization on the internet. JWT (JSON Web Token) is a compact, URL-safe means of representing claims to be transferred between two parties.

## Setting Up the Project

To set up a new Spring Boot project with Spring Security and OAuth2 dependencies, include the following in your `pom.xml`:

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-oauth2-resource-server</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.security</groupId>
        <artifactId>spring-security-oauth2-jose</artifactId>
    </dependency>
    <!-- Other dependencies -->
</dependencies>
