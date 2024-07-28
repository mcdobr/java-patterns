package me.mircea.patterns.design.behavioral.templatemethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrcAI extends GameAI {
    @Override
    void buildStructures() {
        log.info("Building orc structures...");
    }

    @Override
    void buildUnits() {
        log.info("Building orc units...");
    }

    @Override
    void attack() {
        log.info("Orc is attacking...");
    }
}
