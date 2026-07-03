package com.udla.taskmanager.repository;

/**
 * Verifica que InMemoryTaskRepository cumple el contrato de
 * TaskRepository ejecutando la suite heredada de
 * TaskRepositoryContractTest.
 */
class InMemoryTaskRepositoryTest extends TaskRepositoryContractTest {

    @Override
    protected TaskRepository createRepository() {
        return new InMemoryTaskRepository();
    }
}