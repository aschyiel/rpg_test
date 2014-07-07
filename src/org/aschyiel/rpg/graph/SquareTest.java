package org.aschyiel.rpg.graph;


import static org.easymock.EasyMock.*;

import org.easymock.EasyMockRunner;
import org.easymock.Mock; 
import org.aschyiel.rpg.IGameObject;
import org.aschyiel.rpg.graph.ChessBoard.Square;
import org.aschyiel.rpg.level.UnitType;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

public class SquareTest
{

  private Square subject;
  private static ChessBoard bored;
  private IGameObject tank;
  

  @BeforeClass
  public static void setUpBeforeClass()
  { 
    bored = new ChessBoard( 2, 2 );
  }
  
  @Before
  public void setUp() throws Exception
  {
    subject = bored.squares[0][0]; 
    
    tank = createNiceMock( IGameObject.class );
    replay( tank );
  } 
  
  @After
  public void teardown()
  {
    subject.occupant = null;
  }

  @Test
  public void testName()
  {
    assertEquals( subject.name, "A1" );
  }

  @Test
  public void test_isInaccessible_shouldAllowEmptyUnitTypesFreeReign()
  {
    assertEquals( false, subject.isInaccessible( null ) );
  }

  @Test
  public void test_isInaccessible_whenGivenANormalUnitTypeWhenItIsOccupied_shouldReturnTrue()
  {
    subject.occupy( tank );
    assertEquals( true, subject.isInaccessible( UnitType.TANK ) );
  }
  
  @Test
  public void test_isInaccessible_whenGivenANormalUnitTypeWhenItIsOpen_shouldReturnFalse()
  {
    assertNull( subject.occupant );
    assertEquals( false, subject.isInaccessible( UnitType.TANK ) );
  }
  
}
