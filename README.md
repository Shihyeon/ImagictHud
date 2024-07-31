# Imagict Hud

Client-side mod that displays the player's head, nickname, coordinates, indicators, etc. on the in-game HUD.

Please submit issues, improvements, and suggestions [here](https://github.com/Shihyeon/ImagictHud/issues).

The developer is Korean and not proficient in English. We would appreciate it if you could contribute to the translation ([Pull request](https://github.com/Shihyeon/ImagictHud/pulls)).

## Installation

- [Imagict Hud (Modrinth)](https://modrinth.com/mod/imagict-hud)

    > **Dependencies:**
    > 
    > - Required
    >   - [Fabric-API](https://modrinth.com/mod/fabric-api)
    > 
    > - Optional
    >   - [Sodium](https://modrinth.com/mod/sodium) 
    >   - [YetAnotherConfigLib](https://modrinth.com/mod/yacl),
    >   - [ModMenu](https://modrinth.com/mod/modmenu)

## Reporting Issues

- [Issue](https://github.com/Shihyeon/ImagictHud/issues)

## Join the Community

- [Official Discord community](https://discord.gg/vYwV9ZySeK)

## Contribute

Please contribute to the translation in one of two ways:

1. Fork and Pull request

   1. Fork ImagictHud on GitHub
   2. Clone your forked repository (`git clone`)
   3. Create your feature branch from `main` branch (`git checkout -b my-feature`)
   4. Create translation file (`en_us.json`, `ko_kr.json`, `etc.json`)
   5. Translate en_us
   6. Commit your changes (`git commit -am 'Add my feature'`)
   7. Push to the branch (`git push origin my-feature`)
   8. Pull request to `main` branch

2. Local translation and Upload to discord server

   1. Copy en_us.json
   2. Create translation file (en_us, ko_kr, etc)
   3. Translate en_us
   4. Upload file to discord server

## License

- [LGPL-3.0-only](https://github.com/Shihyeon/ImagictHud/blob/main/LICENSE)

## Reference

> - [Health Indicators](https://modrinth.com/mod/health-indicators) 
>   - [Source](https://github.com/AdyTech99/HealthIndicators)
>
> - [Korean Chat Patch](https://modrinth.com/mod/korean-chat-patch)
>   - [Source](https://github.com/najoan125/fabric-koreanchat)
> 
> - [Sodium](https://modrinth.com/mod/sodium)
>   - [Source](https://github.com/CaffeineMC/sodium-fabric)

## Screenshot

### screen

![screen](https://cdn.modrinth.com/data/uWeqs5CX/images/10202d21af7b63e7795d54dbbe2cdc9ba82a1db6.png)

### head mode (bold/flat)

![hud head bold/flat mode](https://cdn.modrinth.com/data/uWeqs5CX/images/2bb09b2b1c522b7e1e0f95d4371ff2ce43b59373.png)

### hud config

**Installing [ModMenu](https://modrinth.com/mod/modmenu) and [YetAnotherConfigLib](https://modrinth.com/mod/yacl), [Sodium](https://modrinth.com/mod/sodium) allows you to manipulate settings from the GUI screen.**

**If you want to change the label color without editing the json directly, you will need [YetAnotherConfigLib](https://modrinth.com/mod/yacl).**

![Sodium options](https://cdn.modrinth.com/data/uWeqs5CX/images/297b18e643c88535424223d34506d06991f1ebbc.png)

![yacl options](https://cdn.modrinth.com/data/uWeqs5CX/images/a02874c86bd74f8554a953c0762d519b402a6136.png)

```json5
{
  "hud": {
    "general": {
      "enable_hud": true
    },
    "display": {
      "enable_head": true,
      "enable_local_date_time_label": false,
      "enable_nickname_label": true,
      "enable_coordinates_label": true,
      "enable_biome_label": true
    },
    "head": {
      "head_render_mode": "BOLD" // ["BOLD", "FLAT"]
    },
    "label": {
      "enable_label_frame": false,
      "label_frame_color": -16777216,
      "label_background_color": -12303292,
      "label_backgound_opacity": 70
    },
    "text": {
      "text_color": -1,
      "enable_text_shadows": false,
      "text_opacity": 90,
      "text_align_mode": "CENTER", // ["LEFT", "CENTER"]
      "local_date_time_mode": "DATE_AND_TIME" // ["DATE_AND_TIME", "DATE", "TIME"]
    },
    "layout": {
      "label_width": 91,
      "label_line_spacing": 0,
      "hud_scale": 0,
      "position_x": 0,
      "position_y": 0,
      "offset": 10
    }
  },
  "indicator": {
    "general": {
      "enable_indicator": true
    },
    "display": {
      "attacking_at": false,
      "looking_at": true,
      "damaged_only": false,
      "duration": 10,
      "reach": 20
    },
    "entities": {
      "player_entities": true,
      "self_player_entity": false,
      "passive_entities": true,
      "hostile_entities": true
    }
  }
}
```