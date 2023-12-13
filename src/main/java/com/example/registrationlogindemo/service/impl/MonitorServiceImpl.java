package com.example.registrationlogindemo.service.impl;

import com.example.registrationlogindemo.dto.MonitorDto;
import com.example.registrationlogindemo.entity.MonTran;
import com.example.registrationlogindemo.repository.MonitorRepository;
import com.example.registrationlogindemo.service.MonitorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class MonitorServiceImpl implements MonitorService {
    MonitorRepository monitorRepository;
    public MonitorServiceImpl(MonitorRepository monitorRepository){
        this.monitorRepository = monitorRepository;
    }
    @Override
    public List<MonitorDto> findAllMonitorTbs() {
        List<MonTran> monitors = monitorRepository.findAll();
        return monitors.stream().map((monitor) -> convertEntityToDto(monitor))
                .collect(Collectors.toList());
    }

    private MonitorDto convertEntityToDto(MonTran monitors) {
        MonitorDto monitorDto = new MonitorDto();
//        monitorDto.setId(monitors.getId());
        monitorDto.setServiceID(monitors.getServiceID());
        monitorDto.setNoTra(monitors.getNoTra());
        monitorDto.setServiceName(monitors.getServiceName());
        monitorDto.setCategory(monitors.getCategory());
        monitorDto.setLastAt(monitors.getLastAt());
        return monitorDto;
    }
}
