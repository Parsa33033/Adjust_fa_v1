import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { openFile, byteSize, Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './specialist.reducer';
import { ISpecialist } from 'app/shared/model/specialist.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ISpecialistProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Specialist = (props: ISpecialistProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { specialistList, match, loading } = props;
  return (
    <div>
      <h2 id="specialist-heading">
        <Translate contentKey="adjustApp.specialist.home.title">Specialists</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="adjustApp.specialist.home.createLabel">Create new Specialist</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {specialistList && specialistList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.specialist.username">Username</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.specialist.firstName">First Name</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.specialist.lastName">Last Name</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.specialist.birth">Birth</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.specialist.gender">Gender</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.specialist.degree">Degree</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.specialist.field">Field</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.specialist.resume">Resume</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.specialist.stars">Stars</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.specialist.image">Image</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.specialist.busy">Busy</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {specialistList.map((specialist, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${specialist.id}`} color="link" size="sm">
                      {specialist.id}
                    </Button>
                  </td>
                  <td>{specialist.username}</td>
                  <td>{specialist.firstName}</td>
                  <td>{specialist.lastName}</td>
                  <td>{specialist.birth ? <TextFormat type="date" value={specialist.birth} format={APP_LOCAL_DATE_FORMAT} /> : null}</td>
                  <td>
                    <Translate contentKey={`adjustApp.Gender.${specialist.gender}`} />
                  </td>
                  <td>{specialist.degree}</td>
                  <td>{specialist.field}</td>
                  <td>{specialist.resume}</td>
                  <td>{specialist.stars}</td>
                  <td>
                    {specialist.image ? (
                      <div>
                        {specialist.imageContentType ? (
                          <a onClick={openFile(specialist.imageContentType, specialist.image)}>
                            <img src={`data:${specialist.imageContentType};base64,${specialist.image}`} style={{ maxHeight: '30px' }} />
                            &nbsp;
                          </a>
                        ) : null}
                        <span>
                          {specialist.imageContentType}, {byteSize(specialist.image)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td>{specialist.busy ? 'true' : 'false'}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${specialist.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${specialist.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${specialist.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="adjustApp.specialist.home.notFound">No Specialists found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ specialist }: IRootState) => ({
  specialistList: specialist.entities,
  loading: specialist.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Specialist);
