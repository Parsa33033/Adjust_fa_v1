import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { byteSize, Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './fitness-program.reducer';
import { IFitnessProgram } from 'app/shared/model/fitness-program.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IFitnessProgramProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const FitnessProgram = (props: IFitnessProgramProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { fitnessProgramList, match, loading } = props;
  return (
    <div>
      <h2 id="fitness-program-heading">
        <Translate contentKey="adjustApp.fitnessProgram.home.title">Fitness Programs</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="adjustApp.fitnessProgram.home.createLabel">Create new Fitness Program</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {fitnessProgramList && fitnessProgramList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.fitnessProgram.type">Type</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.fitnessProgram.description">Description</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {fitnessProgramList.map((fitnessProgram, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${fitnessProgram.id}`} color="link" size="sm">
                      {fitnessProgram.id}
                    </Button>
                  </td>
                  <td>{fitnessProgram.type}</td>
                  <td>{fitnessProgram.description}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${fitnessProgram.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${fitnessProgram.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${fitnessProgram.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="adjustApp.fitnessProgram.home.notFound">No Fitness Programs found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ fitnessProgram }: IRootState) => ({
  fitnessProgramList: fitnessProgram.entities,
  loading: fitnessProgram.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(FitnessProgram);
