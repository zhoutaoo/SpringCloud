redis.call('set','gateway_routes::101','{"uri": "lb://authorization-server:8000", "id": "authorization-server", "predicates": [{ "name": "Path", "args": { "pattern": "/authorization-server/**" } }], "filters":[{ "name": "StripPrefix", "args": { "parts": "1" } }] }');
redis.call('set','gateway_routes::102','{"uri": "lb://authentication-server:8001", "id": "authentication-server", "predicates": [{ "name": "Path", "args": { "pattern": "/authentication-server/**" } }], "filters":[{ "name": "StripPrefix", "args": { "parts": "1" } }] }');

return 2