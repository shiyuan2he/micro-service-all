获取配置规则：根据前缀匹配
/{name}-{profiles}.properties
/{name}-{profiles}.yml
/{name}-{profiles}.json
/{label}/{name}-{profiles}.yml

name 服务名称
profile 环境名称，开发、测试、生产：dev qa prd
lable 仓库分支、默认master分支

匹配原则：从前缀开始。