# randomizer
### Service is developed to be able to generate random values

{type} - ["boolean", "long", "double", "date", "string"]

{min}  - long value

{max}  - long value

specification {
    "type" : {type},
    "min" : {min},
    "max" : {max}
}

GET /{type}

GET /{type}/{min}/{max}

POST / , body = {specification}

POST /collection , body = [{specification}, ...]

POST /entity , body = {fieldName : {specification}, ....}