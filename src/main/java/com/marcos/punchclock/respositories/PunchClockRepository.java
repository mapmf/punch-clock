package com.marcos.punchclock.respositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marcos.punchclock.model.PunchClock;

public interface PunchClockRepository extends JpaRepository<PunchClock, Integer>{

}
