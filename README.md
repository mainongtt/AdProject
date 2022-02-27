# AdProject
### com.my.ad
### 项目介绍
本项目是基于SpringBoot开发的广告系统，主要包括：广告投放系统、广告检索系统。
### 技术架构
![image](https://user-images.githubusercontent.com/52461848/155866041-f21a8473-dd06-4f61-9138-ee780445391e.png)
![image](https://user-images.githubusercontent.com/52461848/155866217-278c5d5c-bb64-44ea-8fd0-688c2ca5e443.png)
本项目采用前后端分离模式，通过 Nginx 代理转发。
- com.my.ad：主要是为了方便我们项目的统一管理
- ad-eurekas：我们可以使用ZK，但是我后期同样会使用我们阿里的NACOS 来替换掉它。
- ad-gateway：网关路由组件
- ad-swagger：swagger文档
- ad-service：主要包括ad-common、ad-dashboard、ad-search、ad-sponsors
- ad-common：主要是一些通用工具类的存放
- ad-dashboard：这个是hystrix提供的可视化管理工具，后期我同样会使用sentinel将其替换掉
- ad-search：广告检索系统
- ad-sponsors：广告投放系统
### 项目环境
- JDK1.8
- MySQL 8+
- Maven 3+
- Spring cloud Greenwich.SR2
- Spring boot 2.1.5
- Kafka 2.2.0
- MySQL Binlog
### 资源环境
- data： 全量增新的数据
- mysql：mysql有关数据
