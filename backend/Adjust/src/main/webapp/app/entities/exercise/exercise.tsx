import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './exercise.reducer';
import { IExercise } from 'app/shared/model/exercise.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IExerciseProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Exercise = (props: IExerciseProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { exerciseList, match, loading } = props;
  return (
    <div>
      <h2 id="exercise-heading">
        <Translate contentKey="adjustApp.exercise.home.title">Exercises</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="adjustApp.exercise.home.createLabel">Create new Exercise</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {exerciseList && exerciseList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.exercise.number">Number</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.exercise.sets">Sets</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.exercise.repsMin">Reps Min</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.exercise.repsMax">Reps Max</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.exercise.move">Move</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.exercise.workout">Workout</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {exerciseList.map((exercise, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${exercise.id}`} color="link" size="sm">
                      {exercise.id}
                    </Button>
                  </td>
                  <td>{exercise.number}</td>
                  <td>{exercise.sets}</td>
                  <td>{exercise.repsMin}</td>
                  <td>{exercise.repsMax}</td>
                  <td>{exercise.moveId ? <Link to={`move/${exercise.moveId}`}>{exercise.moveId}</Link> : ''}</td>
                  <td>{exercise.workoutId ? <Link to={`workout/${exercise.workoutId}`}>{exercise.workoutId}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${exercise.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${exercise.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${exercise.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="adjustApp.exercise.home.notFound">No Exercises found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ exercise }: IRootState) => ({
  exerciseList: exercise.entities,
  loading: exercise.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Exercise);
