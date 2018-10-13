##### 总结：

1. 了解Swing的布局设置

```
JPanel自带空白边距
```
```
父控件设置布局：setLayout(new BorderLayout());
子控件的宽高设置：.setBounds(400, 200, 600, 600); 不生效
可使用：.setPreferredSize(new Dimension(150, 600));控制子控件的宽高
```
2. 了解Swing的监听

3. 了解Swing布局控件的刷新
```
this.rightJPanel.removeAll();//移除所有该控件的内部控件
this.rightJPanel.add(jPanel);//加入新的控件
this.rightJPanel.updateUI();//利用当前外观的值重置 UI 属性
this.rightJPanel.repaint();//重绘此组件。
```
