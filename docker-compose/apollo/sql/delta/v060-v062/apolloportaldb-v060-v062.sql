# delta schema to upgrade apollo portal db from v0.6.0 to v0.6.2

Use ApolloPortalDB;

ALTER TABLE `App` DROP INDEX `NAME`;
CREATE INDEX `IX_NAME` ON App (`Name`(191));
