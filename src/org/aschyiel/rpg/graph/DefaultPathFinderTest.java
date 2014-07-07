package org.aschyiel.rpg.graph;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.aschyiel.rpg.graph.ChessBoard.Square;
import org.aschyiel.rpg.level.UnitType;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DefaultPathFinderTest
{

  private static DefaultPathFinder subject;
  private static ChessBoard bored; 
  
  /**
  * An empty unit-type means that we don't care;
  * Just find the shortest path damn it.
  */
  private static UnitType unitType = null;

   private List<Step> path; 
  
  @BeforeClass
  public static void setUpBeforeClass() throws Exception 
  {
    subject = DefaultPathFinder.getInstance();
    bored = new ChessBoard( 8, 8 );
  }

  @AfterClass
  public static void tearDownAfterClass() throws Exception
  {
  }

  @Before
  public void setUp() throws Exception
  {
    path = new ArrayList<Step>();
  }

  @After
  public void tearDown() throws Exception
  {
  }

  @Test
  public void test_findPath_givenAHorizontalShortestPathFromLeftToRight_shouldReturnAHorizontalPathFromLeftToRightWithinTheSameRow()
  {
    // ie. B2 -> G2.
    Square b2 = getSquare( 1, 1, "B2" );
    Square g2 = getSquare( 1, 6, "G2" );
    subject.findPath( path, b2, g2, unitType );
    assertEquals( 5, path.size() );
    assertEquals( "B2", path.get(0).from.name );
    assertEquals( "C2", path.get(0).to  .name );
    assertEquals( "C2", path.get(1).from.name );
    assertEquals( "D2", path.get(2).from.name );
    assertEquals( "E2", path.get(3).from.name );
    assertEquals( "F2", path.get(4).from.name );
    assertEquals( "G2", path.get(4).to  .name );
  }
  
  /** The opposite of the above test. */
  @Test
  public void test_findPath_shouldWorkFromRightToLeftWithinAHorizontalRow()
  {
    // ie. G2 -> B2.
    Square g2 = getSquare( 1, 6, "G2" );
    Square b2 = getSquare( 1, 1, "B2" );
    subject.findPath( path, g2, b2, unitType );
    assertEquals( 5, path.size() );
    assertEquals( "G2", path.get(0).from.name );
    assertEquals( "F2", path.get(0).to  .name );
    assertEquals( "F2", path.get(1).from.name );
    assertEquals( "E2", path.get(2).from.name );
    assertEquals( "D2", path.get(3).from.name );
    assertEquals( "C2", path.get(4).from.name );
    assertEquals( "B2", path.get(4).to  .name );
  }

  @Test
  public void test_findPath_shouldWorkFromUpToDownWithinAColumn()
  {
    // ie. D2 -> D5.
    Square d2 = getSquare( 1, 3, "D2" );
    Square d5 = getSquare( 4, 3, "D5" );
    subject.findPath( path, d2, d5, unitType);
    assertEquals( 3, path.size() );
    assertEquals( "D2", path.get(0).from.name );
    assertEquals( "D3", path.get(1).from.name );
    assertEquals( "D4", path.get(2).from.name );
    assertEquals( "D5", path.get(2).to  .name );
  }

  /** The opposite of the above. */
  @Test
  public void test_findPath_shouldWorkFromDownToTopWithinAColumn()
  {
    // ie. D5 -> D2.
    Square d5 = getSquare( 4, 3, "D5" );
    Square d2 = getSquare( 1, 3, "D2" );
    subject.findPath( path, d5, d2, unitType);
    assertEquals( 3, path.size() );
    assertEquals( "D5", path.get(0).from.name );
    assertEquals( "D4", path.get(1).from.name );
    assertEquals( "D3", path.get(2).from.name );
    assertEquals( "D2", path.get(2).to  .name );
  } 

  @Test
  public void test_findPath_shouldReportNoPathWhenThereIsNone()
  { 
    Square unconnected = bored.new Square( 99, 99, false );
    Square d5 = getSquare( 4, 3, "D5" );
    subject.findPath( path, d5, unconnected, unitType );
    assertEquals( 0, path.size() );
  } 
  
  private Square getSquare( int m, int n, String name )
  { 
    Square it = bored.squares[m][n];
    assertEquals( name, it.name );
    return it;
  }
  

}
