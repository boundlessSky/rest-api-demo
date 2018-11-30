# rest-api-demo
springboot rest api demo

**代码生成器使用方法：**
maven命令：mvn mybatis-generator:generate -e

**测试方法**
###获取所有学生
GET http://localhost:9000/students/

###根据id查找学生
GET http://localhost:9000/students/10

###创建一条学生信息
POST http://localhost:9000/students/
Accept: */*
Cache-Control: no-cache
Content-Type: application/json;charset=UTF-8

{"stuName":"小赵","sex":false,"age":23,"stuGrade":"三年级","stuClass":"3班"}

###更新一条学生信息
PUT http://localhost:9000/students/10
Accept: */*
Cache-Control: no-cache
Content-Type: application/json;charset=UTF-8

{"stuName":"小王","sex":false,"age":23,"stuGrade":"三年级","stuClass":"3班"}

###删除一条学生信息
DELETE http://localhost:9000/students/7
Accept: */*
Cache-Control: no-cache
