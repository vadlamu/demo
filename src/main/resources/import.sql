-- ROLES
insert into roles(name) values('USER');
insert into roles(name) values('ADMIN');

-- USERS_ADDRESS --street address, apartment number, city, state, and postal code.
insert into users_address(id, street_address, apartment_no, city, state, postal_code) values ('545dfe70-5007-4194-9385-bfd23aa09c0c', '2215 Carribell ct', '', 'powell', 'OH', '43065');
insert into users_address(id, street_address, apartment_no, city, state, postal_code) values ('f147c2a0-4d28-4ced-a416-81dd665275d1', '1013 downtown st', '', 'Edison', 'NY', '65043');

-- USERS
insert into users (id, address_id, first_name, last_name, username, password, bio, nick_name) values ('11111111-1111-1111-1111-111111111111', '545dfe70-5007-4194-9385-bfd23aa09c0c', 'Phil', 'Ingwell', 'PhilIngwell', '$100801$C9/3Y1hA2+6eJTEALhV+5A==$ipi5s0djpXWe0EvTi9lFtU2SnCwAyy1bHoMVpZzV16U=', 'bioooo', 'igloo');
insert into users (id, address_id, first_name, last_name, username, password, bio, nick_name) values ('22222222-2222-2222-2222-222222222222', 'f147c2a0-4d28-4ced-a416-81dd665275d1', 'Anna', 'Conda', 'AnnaConda', '$100801$v7DjKKDDQVtC3V2IhTBD1Q==$PQhrjGolsmANlzC11FFbMixaY4OqjLs3CAaz06rup6A=', 'oooib', 'piggy');

-- USER_ROLES
insert into user_roles(user_id, role_name) values ('11111111-1111-1111-1111-111111111111', 'USER');
insert into user_roles(user_id, role_name) values ('11111111-1111-1111-1111-111111111111', 'ADMIN');
insert into user_roles(user_id, role_name) values ('22222222-2222-2222-2222-222222222222', 'USER');