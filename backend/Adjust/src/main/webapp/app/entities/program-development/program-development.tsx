import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './program-development.reducer';
import { IProgramDevelopment } from 'app/shared/model/program-development.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IProgramDevelopmentProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const ProgramDevelopment = (props: IProgramDevelopmentProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { programDevelopmentList, match, loading } = props;
  return (
    <div>
      <h2 id="program-development-heading">
        <Translate contentKey="adjustApp.programDevelopment.home.title">Program Developments</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="adjustApp.programDevelopment.home.createLabel">Create new Program Development</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {programDevelopmentList && programDevelopmentList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.programDevelopment.date">Date</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.programDevelopment.nutritionScore">Nutrition Score</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.programDevelopment.fitnessScore">Fitness Score</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.programDevelopment.adjustProgram">Adjust Program</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {programDevelopmentList.map((programDevelopment, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${programDevelopment.id}`} color="link" size="sm">
                      {programDevelopment.id}
                    </Button>
                  </td>
                  <td>
                    {programDevelopment.date ? (
                      <TextFormat type="date" value={programDevelopment.date} format={APP_LOCAL_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{programDevelopment.nutritionScore}</td>
                  <td>{programDevelopment.fitnessScore}</td>
                  <td>
                    {programDevelopment.adjustProgramId ? (
                      <Link to={`adjust-program/${programDevelopment.adjustProgramId}`}>{programDevelopment.adjustProgramId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${programDevelopment.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${programDevelopment.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${programDevelopment.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="adjustApp.programDevelopment.home.notFound">No Program Developments found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ programDevelopment }: IRootState) => ({
  programDevelopmentList: programDevelopment.entities,
  loading: programDevelopment.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ProgramDevelopment);
