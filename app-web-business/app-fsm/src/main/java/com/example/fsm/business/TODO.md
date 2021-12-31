# 实现一个运单的 fsm

## 架构

| engine                            | order   |
|-----------------------------------|---------|
| state                             | state   |
| event                             | event   |
| type(fba, cargo)                  | bizCode |
| transportation(air,sea,car,train) |         |

## 定义状态

* [ ] 待检查
* [ ] 创建
* [ ] 发送
* [ ] 接收
* [ ] 完成
* [ ] 取消
* [ ] 删除
* [ ] 其他
* [ ] 退回

# issue

* [x] 当前获取 context.getContext() 无法使用 在 context 的构造器中 setContext(this)
* [x] JPA 使用子类 save
> 调整或去除
> 去除需要改为 save 前，使用 mapstruct 来转换，再 save
