package kg.kstu.sweetshop.repository;

import kg.kstu.sweetshop.models.Staff;

public interface StaffRepository extends BaseRepository<Staff> {
    void add(Staff staff);
    void update(Staff staff);
}
