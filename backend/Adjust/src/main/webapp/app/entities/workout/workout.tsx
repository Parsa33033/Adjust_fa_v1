import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './workout.reducer';
import { IWorkout } from 'app/shared/model/workout.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IWorkoutProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Workout = (props: IWorkoutProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { workoutList, match, loading } = props;
  return (
    <div>
      <h2 id="workout-heading">
        <Translate contentKey="adjustApp.workout.home.title">Workouts</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="adjustApp.workout.home.createLabel">Create new Workout</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {workoutList && workoutList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.workout.dayNumber">Day Number</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.workout.program">Program</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {workoutList.map((workout, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${workout.id}`} color="link" size="sm">
                      {workout.id}
                    </Button>
                  </td>
                  <td>{workout.dayNumber}</td>
                  <td>{workout.programId ? <Link to={`fitness-program/${workout.programId}`}>{workout.programId}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${workout.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${workout.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${workout.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="adjustApp.workout.home.notFound">No Workouts found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ workout }: IRootState) => ({
  workoutList: workout.entities,
  loading: workout.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Workout);
