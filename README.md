# Events Monorepo

This project is a monorepo containing two microservices:

1. **Customer Service** - Manages customer data
2. **Tickets Service** - Manages ticket data
3. **Commons Module** - Share context into modules

## Project Structure

```
events/
├── customer/             # Customer service module
├── tickets/              # Tickets service module
├── commons/              # Shared resources module
├── docker-compose.yml    # Docker Compose configuration
├── Makefile              # Build and run commands
└── README.md             # This file
```

## Prerequisites

- Java 21
- Maven
- Docker
- Docker Compose

## Getting Started

### Building the Project

To build all services:

```bash
make build
```

To build individual services:

```bash
make build-customer
make build-tickets
make build-orchestrator
```

### Running the Project

To run all services:

```bash
make run
```

To run individual services:

```bash
make run-customer
make run-tickets
make run-orchestrator
```

### Stopping the Project

To stop all services:

```bash
make stop
```

### Viewing Logs

To view logs from all services:

```bash
make logs
```

To view logs from individual services:

```bash
make logs-customer
make logs-tickets
make logs-orchestrator
```

### Cleaning Up

To clean up build artifacts and Docker resources:

```bash
make clean
```

## Service Endpoints

- Customer Service: http://localhost:8081
- Tickets Service: http://localhost:8082
- Orchestrator Worker: http://localhost:8083

## Available Make Commands

Run `make help` to see all available commands.