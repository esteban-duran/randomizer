import { TestBed, inject } from '@angular/core/testing';

import { PlayersService } from './players-service.service';

describe('PlayersServiceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [PlayersService]
    });
  });

  it('should be created', inject([PlayersService], (service: PlayersService) => {
    expect(service).toBeTruthy();
  }));
});
