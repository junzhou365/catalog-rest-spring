package catalog.user.dao;

import catalog.user.model.UserRole;

public interface UserRoleDao {
	UserRole updateRole(UserRole userRole, boolean update);
}
