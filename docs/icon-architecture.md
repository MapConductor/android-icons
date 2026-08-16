# MapConductor Icon Collection Architecture

更新日: 2026-08-14

世界展開の追加順序と現地レビュー要件は[Global Icon Collection Roadmap](global-collection-roadmap.md)、現在の成果物名は[`icon-pack-registry.json`](icon-pack-registry.json)を参照する。

## 方針

MapConductorは、地図上で頻繁に使うglyphと、それをマーカーとして表示するcontainerを提供する。
道路・建物・地形など地図本体の描画は対象外とする。

- glyphはMapConductor用に新規制作し、第三者アイコンセットのpathを転用しない。
- 既存セットは、必要な意味・カテゴリ・小サイズでの判別性を調査するためだけに参照する。
- glyphとcontainerを分離し、1つのglyphをpin、circle、bubbleなどへ再利用できるようにする。
- Locale、端末言語、現在地から地域デザインを自動選択しない。
- 使用する共通glyphまたは地域glyphは、アプリ開発者がコードで明示的に選択する。

## パッケージ境界

| 範囲 | Android | iOS | React |
|---|---|---|---|
| 共通glyph・container | `com.mapconductor:icons` | `MapConductorIcons` | `@mapconductor/react-icons` |
| 日本向けglyph | `com.mapconductor:icons-jp` | `MapConductorIconsJP` (`ios-icons-jp`) | `@mapconductor/react-icons-jp` |
| 国・地域向けglyph | `icons-{region}` | `MapConductorIcons{Region}` | `react-icons-{region}` |

地域パックは共通パッケージへ依存し、`MapIconGlyph`の定義だけを追加する。container、描画、bitmap cacheは共通パッケージの実装を利用する。国コードは原則ISO 3166-1 alpha-2の小文字（`jp`、`us`、`gb`など）を用いる。単一国に閉じない文化圏パックが必要な場合は、意味の明確な地域識別子を別途決める。

```text
MapConductorIcons
├── MapIconGlyph
├── CommonMapIcons
└── containers
    ├── PinGlyphIcon
    ├── CircleGlyphIcon       (planned)
    ├── RoundedImageIcon      (planned)
    └── InfoBubbleIcon        (planned)

MapConductorIconsJP
└── JapanMapIcons
    ├── postOffice
    ├── policeStation
    └── ...
```

呼び出し側が明示的に選択する。

```text
PinGlyphIcon(glyph = CommonMapIcons.hospital)
PinGlyphIcon(glyph = JapanMapIcons.postOffice)
```

`CommonMapIcons.postOffice`と`JapanMapIcons.postOffice`は、同じ施設種別でも異なるglyph IDを持つ独立した選択肢である。共通版から日本版への暗黙のfallbackや置換は行わない。

## Glyph仕様

- IDは安定したASCII `lower_snake_case` とする。
- 共通glyph IDは `hospital` のように意味だけを表す。
- 地域glyphの完全修飾IDは `jp.post_office` のように地域を含める。
- 正本は24×24 viewBoxのSVG pathとする。
- 原則として単色maskとし、色はcontainerから与える。
- 16px相当でも輪郭が潰れないよう、細線、微小な穴、過剰なディテールを避ける。
- 同じIDの意味はプラットフォーム間およびversion間で変更しない。意味変更は新しいIDで追加する。
- SVG、Android、Swift、TypeScriptの生成物が同じ正本から作られる構成を目標とする。

## Container仕様

containerはglyphの意味を変更せず、背景形状、色、枠、画像crop、anchor、選択状態だけを担当する。

最初の共通APIは`PinGlyphIcon`とする。既存のデフォルトピンと同じ輪郭・anchorを使い、円形部分の中央へglyphを配置する。bitmap化した結果は各SDKのLRU/NSCacheへ保存し、icon instanceが画像を永続的に保持しない。

次に、以下を順に追加する。

1. `CircleGlyphIcon`: 円形badge内のglyph
2. `RoundedImageIcon`: 角丸長方形への写真・ロゴのcenter crop
3. `InfoBubbleIcon`: glyphまたは画像とテキストの組み合わせ
4. 選択・警告・件数などの装飾

## オリジナル制作とレビュー

各glyphには、ID、意味、地域範囲、正本SVG、制作日、レビュー状態を記録する。レビューでは次を確認する。

1. 第三者素材のpathを複製していないこと
2. 16px、20px、24pxで意味を判別できること
3. 塗りつぶしと反転色の両方で破綻しないこと
4. 既存glyphと意味やシルエットが不要に重複しないこと
5. 地域固有記号を共通コレクションへ混在させていないこと

## 初期実装

`hospital`を最初の共通glyphとする。24×24内に収めた医療十字のオリジナルpathを用い、3プラットフォームの`CommonMapIcons`から同じIDで公開する。正本は[`artwork/hospital.svg`](../artwork/hospital.svg)、生成情報は[`artwork/manifest.json`](../artwork/manifest.json)に置く。この1件でデータモデル、container、cache、公開API、描画テストを確立してから、カタログをカテゴリ単位で追加する。
