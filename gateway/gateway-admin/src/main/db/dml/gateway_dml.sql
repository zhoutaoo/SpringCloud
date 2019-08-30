--用户
INSERT INTO gateway_route (id, route_id, uri, predicates, filters, orders, description, status, created_time, updated_time, created_by, updated_by)
VALUES
  (101,
   'authorization-server',
   'lb://authorization-server:8000',
   '[{"name":"Path","args":{"pattern":"/authorization-server/**"}}]',
   '[{"name":"StripPrefix","args":{"parts":"1"}}]',
   100,
   '授权认证服务网关注册',
   'Y', now(), now(), 'system', 'system'),
  (102,
   'authentication-server',
   'lb://authentication-server:8001',
   '[{"name":"Path","args":{"pattern":"/authentication-server/**"}}]',
   '[{"name":"StripPrefix","args":{"parts":"1"}}]',
   100,
   '签权服务网关注册',
   'Y', now(), now(), 'system', 'system')

