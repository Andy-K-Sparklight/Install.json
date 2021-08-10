# Install.json 使用指南

Install.json 是 Alicorn Launcher 的附加组件，但和 Alicorn 一样，它是完全的自由软件，可以被用于任何用途。然而，它的主要功能是**辅助整合包作者分发整合包**，仅仅需要一份 JSON
文件！（如果你打算只支持 Alicorn，甚至可以只需要一段文本！）

Install.json 帮助安装完成最后一步，即复制 Mods、资源包、光影包等。

## 参数说明

Install.json 的参数除目标目录外，全部由 `-Dkey=value` 进行指定。

### `rfiles`

指定所有要处理的文件，无论是 Jar 还是其它类型的资源文件，通常应当由启动器生成，即 `${classpath}`。

### `rignore`

要忽略的文件，任何文件名只要包含 `rignore` 所指定的名称中的任何一个，就不会被部署。通常这里在生成时就会指定，一个为安装器本身（`installer-x.x.x.jar`
），一个为整合包的（即使没有）虚拟客户端（`<name>.jar`）。

### `rsuggest` 和 `rversion`

Install.json 不能完成 Forge 和 Fabric 的安装，因此在安装完成后会通过 UI 给用户一个提示，这两个分别指示 Mod 加载器名称和 Minecraft 版本。

### `rshowhint`

是否显示完成提示，值为 `Hint` 时表示显示，其它值都表示隐藏。

### `rshowlicense`

是否在启动时将许可声明打印到控制台，值为 `1` 时表示打印声明，否则仅打印简短说明。

### 目标目录

在主类之后进行指定，通常指向游戏目录，即 `${game_directory}`。

## 启动档案规范

Install.json 使用新版本档案（1.13 及以后）。

档案的名称应当是 `<name>-Installer`，虽然不是强制，但这有助于区分安装器和正常的可启动核心。

若要 Install.json 正确识别资源，你需要将要补全的资源放置在 `libraries` 中：

```json
{
  "downloads": {
    "artifact": {
      "path": "rarityeg/installjson/inject/mods/examplemod/1.0.0/examplemod-1.0.0.jar",
      "sha1": "...",
      "size": 0,
      "url": "..."
    },
    "name": "rarityeg.installjson.inject.mods.examplemod"
  }
}
```

Install.json 仅关心启动器最终提供给它的参数，因此它会检测 `rarityeg/installjson/inject/` 这一特征字符，然后将后面的部分用路径分隔符断开，取出第一段作为**目标文件夹**（`mods`
），取出最后一段作为**文件名**（`examplemod-1.0.0.jar`），并执行文件的复制。要指定到根目录，使用 `injectintoroot`。

## 许可证

Install.json 是采用 GNU 通用公共许可证（第三版）进行许可的自由软件。

Copyright (C) 2021 Andy K Rarity Sparklight

### 您可以自由地……

- 按你的意愿运行此软件

- 学习软件如何工作，或按你的意愿修改软件

- 分发软件副本

- 将你修改过的软件版本再分发给其他人

- ……其它 GPL 3.0 规定的许可

### ……惟须遵守以下条件：

- 不得对 GPL 3.0 授予的权利施加「进一步限制」

- 分发已修改的软件也必须使用 GPL 3.0

- ……其它 GPL 3.0 规定的条件

*以上内容是为了让你更好理解你能够做什么以及需要做什么，若与许可证本身有出入，以许可证中的相关条款为准。*

请记住我们不阻止任何自由使用软件的行为，我们**只阻止那些限制你的自由的行为**。
