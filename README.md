# Litho学习记录

## 环境搭建
   * 初始化。**application.java -> onCreate**

```

	SoLoader.init(this, false);
```

   * 创建一个基本视图
 
```

	final ComponentContext context = new ComponentContext(this);
	final Component component = Text.create(context).text("Hello World")
	        .textSizeDip(50)
	        .build();
	setContentView(LithoView.create(context, component));
```

   * 创建一个自定义组件，组件的名字必须以Spec结尾,如***MyComponentSpec.java***，在类名上加上注解**@LayoutSpec**

```

	@OnCreateLayout
	static Component onCreateLayout(ComponentContext c) {
	  return Column.create(c)
	        .paddingDip(ALL, 16)
	        .backgroundColor(Color.WHITE)
	        .child(
	            Text.create(c)
	                .text("Hello world")
	                .textSizeSp(40))
	        .child(
	            Text.create(c)
	                .text("Litho tutorial")
	                .textSizeSp(20))
	        .build();
}

```

   * 运行命令编译一个代码以生成***MyComponent.java***,在根目录运行命令 `gradlew assemble`

   
```

	final ComponentContext context = new ComponentContext(this);
	final Component component = MyComponent.create(context).build();
	setContentView(LithoView.create(context, component));
```

   * **创建一个类似RecyclerView的可回收视频**
      - 定义一个组件类(类名以Spec结尾)
      - 类名加上注解 `@GroupSectionSpec`
      - 方法名加上注解 `@OnCreateChildren`

```

		@GroupSectionSpec
		public class ListSectionSpec {
		
		    @OnCreateChildren
		    static Children onCreateChildren(final SectionContext context) {
		        Children.Builder builder = Children.create();
		        for (int i = 0; i < 32; i++) {
		            builder.child(
		                    SingleComponentSection.create(context)
		                    .key(String.valueOf(i))
		                    .component(MyComponent.create(context).build())
		            );
		        }
		        return builder.build();
		    }
		}

```

   * 之后在Activity中调用

        final ComponentContext context = new ComponentContext(this);

        final Component component = RecyclerCollectionComponent.create(context)
                .disablePTR(true)
                .section(ListSection.create(new SectionContext(context)).build()).build();

        setContentView(LithoView.create(this, component));


# 定义Layout Spec注意点
	
	1.@OnCreateLayout的第一个方法必须为ComponentContext
	
