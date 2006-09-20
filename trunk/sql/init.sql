INSERT INTO users ( account, password, name, email ) VALUES ( 'snwiem', 'secret', 'Sebastian Wiemer', 'sebastian.wiemer@nefkom.net' );
INSERT INTO users ( account, password, name, email ) VALUES ( 'snbreu', 'secret', 'Stephan Breu', 'stephan.breu@gfk.com' );
INSERT INTO users ( account, password, name, email ) VALUES ( 'fdbisc', 'secret', 'Fred Bischoff', 'fred,bischoff@gfk.com' );
INSERT INTO modules ( ident, name, description, leader, creator, created, modifier, modified ) values ( 'MTST', 'Module One', 'My first module', 1, 1, now(), 1, now() );

INSERT INTO issue_types ( name, icon, description ) VALUES ( 'Bug', NULL, 'A bug.' );
INSERT INTO issue_types ( name, icon, description ) VALUES ( 'Feature', NULL, 'A new feature.' );
INSERT INTO issue_types ( name, icon, description ) VALUES ( 'Wish', NULL, 'Something to discuss.' );

INSERT INTO issue_priorities ( name, icon, description ) VALUES ( 'Blocker', NULL, 'Blocking component success.' );
INSERT INTO issue_priorities ( name, icon, description ) VALUES ( 'Critical', NULL, 'Critical issue which should be resolved very urgently.' );
INSERT INTO issue_priorities ( name, icon, description ) VALUES ( 'Major', NULL, 'A major issue. Resolvement is of mid priority.' );
INSERT INTO issue_priorities ( name, icon, description ) VALUES ( 'Minor', NULL, 'A minor issue.' );
INSERT INTO issue_priorities ( name, icon, description ) VALUES ( 'Trivial', NULL, 'No really important.' );

INSERT INTO issue_status ( name, pos, icon, description ) VALUES ( 'Open', 0, NULL, 'A new issue.' );
INSERT INTO issue_status ( name, pos, icon, description ) VALUES ( 'Verified', 1, NULL, 'The issue is verified and ready to be resolved.' );
INSERT INTO issue_status ( name, pos, icon, description ) VALUES ( 'In Progress', 2, NULL, 'The issue is currently worked on.' );
INSERT INTO issue_status ( name, pos, icon, description ) VALUES ( 'Resolved', 3, NULL, 'The issue has been resolved and can be tested.' );
INSERT INTO issue_status ( name, pos, icon, description ) VALUES ( 'Tested', 4, NULL, 'The issue has been tested any may be closed.' );
INSERT INTO issue_status ( name, pos, icon, description ) VALUES ( 'Reopened', 5, NULL, 'The issue didn''t pass the test.' );
INSERT INTO issue_status ( name, pos, icon, description ) VALUES ( 'Closed', 6, NULL, 'The issue is closed.' );