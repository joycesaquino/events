# Events Project Makefile

.PHONY: help build run stop clean test

help: ## Show this help
	@echo "Events Project Makefile"
	@echo ""
	@echo "Usage: make [target]"
	@echo ""
	@echo "Targets:"
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-20s\033[0m %s\n", $$1, $$2}'

build: ## Build all services
	./mvnw clean package -DskipTests
	docker-compose build

run: ## Run all services
	docker-compose up -d

stop: ## Stop all services
	docker-compose down

clean: ## Clean up build artifacts and docker resources
	./mvnw clean
	docker-compose down -v
	docker system prune -f

test: ## Run tests for all services
	./mvnw test

build-customer: ## Build only customer service
	./mvnw clean package -DskipTests -pl customer
	docker-compose build customer-service

build-tickets: ## Build only tickets service
	./mvnw clean package -DskipTests -pl tickets
	docker-compose build tickets-service

build-orchestrator: ## Build only orchestrator worker
	./mvnw clean package -DskipTests -pl orchestrator
	docker-compose build orchestrator-worker

run-customer: ## Run only customer service
	docker-compose up -d customer-service

run-tickets: ## Run only tickets service
	docker-compose up -d tickets-service

run-orchestrator: ## Run only orchestrator worker
	docker-compose up -d orchestrator-worker

logs: ## View logs from all services
	docker-compose logs -f

logs-customer: ## View logs from customer service
	docker-compose logs -f customer-service

logs-tickets: ## View logs from tickets service
	docker-compose logs -f tickets-service

logs-orchestrator: ## View logs from orchestrator worker
	docker-compose logs -f orchestrator-worker